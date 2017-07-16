package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.entity.Record;

/**
 * 
 * @author Mr.Zhang
 * @describe 记录信息访问层
 */
public interface RecordDao {

	public List<Record> getAllRecord(Map<String, Object> map);

	public int countNum(Map<String, Object> map);

	public int addRecord(Record record);

	public Record serchRecordByID(int id);

	public int updateRecord(Record record);

}
