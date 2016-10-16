package com.ecomerce.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ecomerce.converter.base.BaseVoConverter;
import com.ecomerce.dao.custom.base.impl.FindConverterDao;
import com.ecomerce.entities.Agama;
import com.ecomerce.util.ExtendedSpringBeanUtil;
import com.ecomerce.vo.AgamaVO;

/**
 * Converter class between AgamaM and AgamaVO
 * 
 * @author Roberto
 */
@Component
public class AgamaConverter extends FindConverterDao implements
		BaseVoConverter<AgamaVO, Agama> {

	public Agama transferVOToModel(AgamaVO vo, Agama model) {
		if (null == model)
			model = new Agama();

		try {
			String[] fieldsToInclude = null;
			Map<String, Object> serialized = vo.serialize(fieldsToInclude,vo.getClass().getName());
			Gson gson = new Gson();
	        String json = gson.toJson(serialized);
	        model = gson.fromJson(json, Agama.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;

	}

	public List<AgamaVO> transferListOfModelToListOfVO(List<Agama> models,
			List<AgamaVO> vos) {
		if (null == vos)
			vos = new ArrayList<AgamaVO>();

		if (null == models)
			return vos;

		for (Agama agama : models) {
			AgamaVO agamaVO = new AgamaVO();
			transferModelToVO(agama, agamaVO);
			vos.add(agamaVO);
		}

		return vos;
	}

	public AgamaVO transferModelToVO(Agama model, AgamaVO vo) {
		if (null == vo)
			vo = new AgamaVO();

		// fix this
		ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
				new String[] { "agama", }, new String[] { "agama" });
//		LoginUserVO test = loginUserService.findById(new Integer(1));
//		System.out.println("tea = " + test.getKataSandi());
		return vo;
	}
	

}
