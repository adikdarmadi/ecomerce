package com.ecomerce.service;

import java.io.IOException;
import java.util.List;

import com.ecomerce.entities.Agama;
import com.ecomerce.vo.AgamaVO;
import com.ecomerce.vo.TestUploadVO;

/**
 * Agama Service
 * 
 * @author Roberto
 */
public interface AgamaService <T> extends BaseVoService<Agama, AgamaVO, Integer> {
	String GetSettingDataFixed(String prefix) ;
	List<Agama> findAllAgama();
	Agama findByIdAgama(Integer id);
	TestUploadVO upload(TestUploadVO vo) throws IOException;

}
