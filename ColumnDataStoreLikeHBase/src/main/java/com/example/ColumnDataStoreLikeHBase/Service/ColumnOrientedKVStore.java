package com.example.ColumnDataStoreLikeHBase.Service;

import com.example.ColumnDataStoreLikeHBase.Entity.CellValue;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnFamily;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnQualifier;
import com.example.ColumnDataStoreLikeHBase.Entity.RowKey;

import java.util.Map;

public interface ColumnOrientedKVStore {
    void put(RowKey rowKey, ColumnFamily columnFamily, ColumnQualifier columnQualifier, String value, long timestamp);
    CellValue get(RowKey key, ColumnFamily columnFamily, ColumnQualifier columnQualifier);
    Map<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> scan(RowKey startValue, RowKey endValue, ColumnFamily columnFamily); //Column family is crucial to locating and storing data physically and logically within a family. Efficient scans are per family.

}
