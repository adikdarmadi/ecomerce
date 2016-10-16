package com.ecomerce.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.converter.AgamaConverter;
import com.ecomerce.dao.AgamaDao;
import com.ecomerce.entities.Agama;
import com.ecomerce.exception.ServiceVOException;
import com.ecomerce.service.AgamaService;
import com.ecomerce.util.CommonUtil;
import com.ecomerce.vo.AgamaVO;
import com.ecomerce.vo.TestUploadVO;

@Service("agamaService")
@EnableCaching
public class AgamaServiceImpl extends BaseVoServiceImpl implements AgamaService<AgamaVO> {

	@Autowired
	private AgamaDao agamaDao;

	@Autowired
	public AgamaConverter agamaConverter;

	@Override
	@Transactional(readOnly = false)
	public AgamaVO add(AgamaVO vo) throws JpaSystemException, ServiceVOException {
		Agama agama = agamaConverter.transferVOToModel(vo, new Agama());
		Agama resultModel = agamaDao.save(agama);

		return vo;
	}

	@Override
	@Transactional(readOnly = false)
	public AgamaVO update(AgamaVO vo) throws JpaSystemException, ServiceVOException {
		Agama agama = agamaDao.findOne(vo.getId());
		agama = agamaConverter.transferVOToModel(vo, agama);

		agamaDao.save(agama);
		return vo;

	}

	@Override
	public Boolean delete(Integer key) throws JpaSystemException {
		if (CommonUtil.isNotNullOrEmpty(key)) {
			Agama agama = agamaDao.findOne(key);
			agamaDao.delete(agama);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AgamaVO findById(Integer key) throws JpaSystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AgamaVO> findAll() throws JpaSystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Map<String, Object> findAllWithPageAndLimitAndSortByAndDirectionParameter(Integer page, Integer limit,
			String sort, String dir) {
		List<AgamaVO> agamaVOList = new ArrayList<AgamaVO>();
		Pageable pageable = new PageRequest(page, limit, getSortBy(sort, dir));
		Page<Agama> resultPage = agamaDao.findAll(pageable);
		List<Agama> ruanganList = resultPage.getContent();

		agamaConverter.transferListOfModelToListOfVO(ruanganList, agamaVOList);

		return constructMapReturn(agamaVOList, resultPage.getTotalElements(), resultPage.getTotalPages());
	}

	@Override
	public List<Agama> findAllAgama() {
		List<Agama> models = (List<Agama>) agamaDao.findAll();
		return models;
	}

	@Override
	@Cacheable(value = "testFindCache", key = "#id")
	public Agama findByIdAgama(Integer id) {
		Agama a = agamaDao.findByIdAgama(id);
		return a;
	}

	private void slowQuery(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	public Agama multipleQuery(Integer id) {
		slowQuery(2000L);
		Agama a = agamaDao.findByIdAgama(id);
		return a;
	}

	@Override
	public TestUploadVO upload(TestUploadVO vo) throws IOException {
		  byte[] decodedBytes = Base64.decodeBase64(vo.getFileInput());
		  String targetFile="d:/"+vo.getFileName();
	      writeByteArraysToFile(targetFile, decodedBytes);
		return null;
	}

	public static void writeByteArraysToFile(String fileName, byte[] content) throws IOException {

		File file = new File(fileName);
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(content);
		writer.flush();
		writer.close();

	}

}
