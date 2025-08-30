package com.example.ColumnDataStoreLikeHBase.Service;

import com.example.ColumnDataStoreLikeHBase.Entity.CellValue;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnFamily;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnQualifier;
import com.example.ColumnDataStoreLikeHBase.Entity.RowKey;

import java.util.*;

public class InmemoryRowWiseKVStore implements ColumnOrientedKVStore{

    Set<ColumnFamily> predefinedFamilies;
    NavigableMap<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> store = new TreeMap<>(Comparator.comparing(RowKey::getKey));

    public InmemoryRowWiseKVStore(String[] predefinedFamilies) {
        this.predefinedFamilies = new HashSet<>();
        for(int i=0;i<predefinedFamilies.length;i++)
        {
            this.predefinedFamilies.add(new ColumnFamily(predefinedFamilies[i]));
        }
    }

    @Override
    public void put(RowKey rowKey, ColumnFamily columnFamily, ColumnQualifier columnQualifier, String value, long timestamp) {
        if(!this.predefinedFamilies.contains(columnFamily))
            return ;
        Map<ColumnFamily, Map<ColumnQualifier, CellValue>> columnFamilyMap = store.get(rowKey);
        if (columnFamilyMap == null) {
            columnFamilyMap = new HashMap<>();
            store.put(rowKey, columnFamilyMap);
        }

        Map<ColumnQualifier, CellValue> qualifierMap = columnFamilyMap.get(columnFamily);
        if(qualifierMap == null )
        {
            qualifierMap = new HashMap<>();
            columnFamilyMap.put(columnFamily, qualifierMap);
        }
        qualifierMap.put(columnQualifier, new CellValue(value, timestamp));
    }

    @Override
    public CellValue get(RowKey key, ColumnFamily columnFamily, ColumnQualifier columnQualifier) {
        Map<ColumnFamily, Map<ColumnQualifier, CellValue>> columnFamilyMap = store.get(key);
        if(columnFamilyMap == null)
            return null;
        Map<ColumnQualifier, CellValue> qualifierMap = columnFamilyMap.get(columnFamily);
        if(qualifierMap ==null)
            return null;
        return qualifierMap.get(columnQualifier);
    }

    @Override
    public Map<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> scan(RowKey startValue, RowKey endValue, ColumnFamily columnFamily) {
        return store.subMap(startValue, true, endValue, true);
    }
}
