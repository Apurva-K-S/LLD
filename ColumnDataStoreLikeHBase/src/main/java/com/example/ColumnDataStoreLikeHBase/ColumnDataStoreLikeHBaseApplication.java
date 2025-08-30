package com.example.ColumnDataStoreLikeHBase;

import com.example.ColumnDataStoreLikeHBase.Entity.CellValue;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnFamily;
import com.example.ColumnDataStoreLikeHBase.Entity.ColumnQualifier;
import com.example.ColumnDataStoreLikeHBase.Entity.RowKey;
import com.example.ColumnDataStoreLikeHBase.Service.ColumnOrientedKVStore;
import com.example.ColumnDataStoreLikeHBase.Service.InmemoryRowWiseKVStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class ColumnDataStoreLikeHBaseApplication {

	public static void main(String[] args) {
		// ROW-WISE IMPLEMENTATION
		ColumnOrientedKVStore inmemoryRowWiseKVStore = new InmemoryRowWiseKVStore(new String[]{"personal","contact"});
		RowKey row1 = new RowKey("use");
		ColumnFamily personal = new ColumnFamily("personal");
		ColumnFamily contact = new ColumnFamily("contact");

		// put example
		inmemoryRowWiseKVStore.put(row1, personal, new ColumnQualifier("name"), "Apurva", System.currentTimeMillis());

		CellValue nameValue = inmemoryRowWiseKVStore.get(row1, personal, new ColumnQualifier("name"));
		if (nameValue != null) {
			System.out.println("Name for " + row1.getKey() + ": " + nameValue.getValue());
		} else {
			System.out.println("No value found for qualifier name");
		}

		// get example
		CellValue ageValue = inmemoryRowWiseKVStore.get(row1, personal, new ColumnQualifier("age"));
		if (ageValue != null) {
			System.out.println("age for " + row1.getKey() + ": " + ageValue.getValue());
		} else {
			System.out.println("No value found for qualifier age");
		}

		// Scan range example
		RowKey startKey = new RowKey("user");
		RowKey endKey = new RowKey("userz");
		Map<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> results = inmemoryRowWiseKVStore.scan(startKey, endKey,personal);
		for (RowKey rowKey : results.keySet()) {
			System.out.println("RowKey: " + rowKey.getKey());
			Map<ColumnFamily, Map<ColumnQualifier, CellValue>> cfMap = results.get(rowKey);
			for (ColumnFamily cf : cfMap.keySet()) {
				System.out.println(" ColumnFamily: " + cf.getName());
				Map<ColumnQualifier, CellValue> qualifierMap = cfMap.get(cf);
				for (ColumnQualifier q : qualifierMap.keySet()) {
					System.out.println("  ColumnQualifier: " + q.getName() + " = " + qualifierMap.get(q).getValue());
				}
			}
		}

		System.out.println("\n\n\n");
		// COLUMN-WISE IMPLEMENTATION
		ColumnOrientedKVStore inmemoryColumnWiseKVStore = new InmemoryRowWiseKVStore(new String[]{"personal","contact"});
		RowKey row2 = new RowKey("user12");

		// put example
		inmemoryColumnWiseKVStore.put(row2, personal, new ColumnQualifier("name"), "Dheer", System.currentTimeMillis());

		CellValue nameValue1 = inmemoryColumnWiseKVStore.get(row2, personal, new ColumnQualifier("name"));
		if (nameValue1 != null) {
			System.out.println("Name for " + row2.getKey() + ": " + nameValue1.getValue());
		} else {
			System.out.println("No value found for qualifier name");
		}

		// get example
		CellValue ageValue1 = inmemoryColumnWiseKVStore.get(row2, personal, new ColumnQualifier("age"));
		if (ageValue != null) {
			System.out.println("age for " + row2.getKey() + ": " + ageValue1.getValue());
		} else {
			System.out.println("No value found for qualifier age");
		}

		// Scan range example
		RowKey startKey1 = new RowKey("user0");
		RowKey endKey1 = new RowKey("user9");
		Map<RowKey, Map<ColumnFamily, Map<ColumnQualifier, CellValue>>> results1 = inmemoryColumnWiseKVStore.scan(startKey1, endKey1,personal);
		for (RowKey rowKey : results1.keySet()) {
			System.out.println("RowKey: " + rowKey.getKey());
			Map<ColumnFamily, Map<ColumnQualifier, CellValue>> cfMap = results1.get(rowKey);
			for (ColumnFamily cf : cfMap.keySet()) {
				System.out.println(" ColumnFamily: " + cf.getName());
				Map<ColumnQualifier, CellValue> qualifierMap = cfMap.get(cf);
				for (ColumnQualifier q : qualifierMap.keySet()) {
					System.out.println("  ColumnQualifier: " + q.getName() + " = " + qualifierMap.get(q).getValue());
				}
			}
		}
	}

}
