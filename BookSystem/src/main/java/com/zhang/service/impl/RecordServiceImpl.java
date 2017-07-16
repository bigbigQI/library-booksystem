package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.dao.RecordDao;
import com.zhang.entity.Record;
import com.zhang.service.RecordService;

@Service("recordService")
@Transactional

public class RecordServiceImpl implements RecordService {

	@Resource
	private RecordDao recordDao;

	@Override
	public List<Record> getAllRecord(Map<String, Object> map) {
		return recordDao.getAllRecord(map);
	}

	@Override
	public int countNum(Map<String, Object> map) {
		return recordDao.countNum(map);
	}

	@Override
	public int addRecord(Record record) {
		return recordDao.addRecord(record);
	}

	@Override
	public Record serchRecordByID(int id) {
		return recordDao.serchRecordByID(id);
	}

	@Override
	public int updateRecord(Record record) {
		return recordDao.updateRecord(record);
	}

}
