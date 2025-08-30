package com.example.ColumnDataStoreLikeHBase.Service;

import com.example.ColumnDataStoreLikeHBase.Entity.CellValue;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnFamily;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnQualifier;
import com.example.ColumnDataStoreLikeHBase.Entity.RowKey;

import java.util.*;

public class InmemoryColumnWiseKVStore implements ColumnOrientedKVStore{

    Map<ColumnFamily, NavigableMap<RowKey, Map<ColumnQualifier, CellValue>>> store = new HashMap<>();
    Set<ColumnFamily> predefinedFamilies;

    public InmemoryColumnWiseKVStore(String[] predefinedFamilies) {
        this.predefinedFamilies = new HashSet<>();
        for(int i=0;i<predefinedFamilies.length;i++)
        {
            this.predefinedFamilies.add(new ColumnFamily(predefinedFamilies[i]));
        }
    }

    @Override
    public void put(RowKey rowKey, ColumnFamily columnFamily, ColumnQualifier columnQualifier, String value, long timestamp) {
        if(!this.predefinedFamilies.contains(columnFamily))
        {
            System.out.println("column family doesnt exist");
            return;
        }

        NavigableMap<RowKey, Map<ColumnQualifier, CellValue>> rowKeyMapNavigableMap = store.get(columnFamily);
        if(rowKeyMapNavigableMap == null)
        {
            rowKeyMapNavigableMap = new TreeMap<>(Comparator.comparing(RowKey::getKey));
            store.put(columnFamily, rowKeyMapNavigableMap);
        }
        Map<ColumnQualifier, CellValue> qualifierMap = rowKeyMapNavigableMap.get(rowKey);
        if (qualifierMap == null) {
            qualifierMap = new HashMap<>();
            rowKeyMapNavigableMap.put(rowKey, qualifierMap);
        }
        qualifierMap.put(columnQualifier, new CellValue(value, timestamp));
    }

    @Override
    public CellValue get(RowKey rowKey, ColumnFamily columnFamily, ColumnQualifier columnQualifier) {
        NavigableMap<RowKey, Map<ColumnQualifier, CellValue>> cfMap = store.get(columnFamily);
        if (cfMap == null) return null;
        Map<ColumnQualifier, CellValue> qualifiersMap = cfMap.get(rowKey);
        if (qualifiersMap == null) return null;
        return qualifiersMap.get(columnQualifier);

    }

    @Override
    public Map<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> scan(RowKey startValue, RowKey endValue, ColumnFamily columnFamily) {

        NavigableMap<RowKey, Map<ColumnQualifier, CellValue>> cfMap = store.get(columnFamily);
        if (cfMap == null) {
            return Collections.emptyMap();
        }

        SortedMap<RowKey, Map<ColumnQualifier, CellValue>> subMap = cfMap.subMap(startValue, true, endValue, true);

        // Wrap the result to match the return type of row-wise implementation
        Map<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> result = new LinkedHashMap<>();
        for (Map.Entry<RowKey, Map<ColumnQualifier, CellValue>> entry : subMap.entrySet()) {
            Map<ColumnFamily, Map<ColumnQualifier, CellValue>> familyMap = new HashMap<>();
            familyMap.put(columnFamily, entry.getValue());
            result.put(entry.getKey(), familyMap);
        }
        return result;
    }
}
