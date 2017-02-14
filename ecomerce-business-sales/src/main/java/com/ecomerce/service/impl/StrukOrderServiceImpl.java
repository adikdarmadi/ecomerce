package com.ecomerce.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.dao.StrukOrderDao;
import com.ecomerce.entities.DetailKomponenHarga;
import com.ecomerce.entities.Produk;
import com.ecomerce.entities.StrukOrder;
import com.ecomerce.entities.StrukOrderDetail;
import com.ecomerce.service.StrukOrderService;
import com.ecomerce.util.CommonUtil;
import com.ecomerce.vo.DetailKomponenHargaVO;
import com.ecomerce.vo.ProdukVO;
import com.ecomerce.vo.StrukOrderDetailVO;
import com.ecomerce.vo.StrukOrderVO;

@Service("strukOrderService")
public class StrukOrderServiceImpl implements StrukOrderService {

	@Autowired
	private StrukOrderDao strukOrderDao;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Map<String,Object> saveStrukOrder(StrukOrderVO strukOrderVo) {
		StrukOrder strukOrder=new StrukOrder();
		List<StrukOrderDetail> tmpStrukOrderDetail=new ArrayList<StrukOrderDetail>();
		if(CommonUtil.isNotNullOrEmpty(strukOrderVo.getStrukOrders())){
			for(StrukOrderDetailVO strukOrderDetailVO:strukOrderVo.getStrukOrders()){
				StrukOrderDetail strukOrderDetail = modelMapper.map(strukOrderDetailVO, StrukOrderDetail.class);
				strukOrderDetail.setStrukOrder(strukOrder);
				Set<DetailKomponenHarga> detailKomponenHarga=new HashSet<DetailKomponenHarga>();
				if(CommonUtil.isNotNullOrEmpty(strukOrderDetailVO.getDetailKomponenHarga())){
					for(DetailKomponenHargaVO detailKomponenHargaVO:strukOrderDetailVO.getDetailKomponenHarga()){
						DetailKomponenHarga model=modelMapper.map(detailKomponenHargaVO, DetailKomponenHarga.class);
						model.setStrukOrderDetail(strukOrderDetail);
						detailKomponenHarga.add(model);
					}
				}
				strukOrderDetail.getDetailKomponenHarga().clear();
				strukOrderDetail.getDetailKomponenHarga().addAll(detailKomponenHarga);
				tmpStrukOrderDetail.add(strukOrderDetail);
			}
		}
		strukOrder.getStrukOrders().clear();
		strukOrder.getStrukOrders().addAll(tmpStrukOrderDetail);
		strukOrder=strukOrderDao.save(strukOrder);
		
		Map<String,Object> result=new HashMap<String,Object>(); 
		result.put("id", strukOrder.getId());
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> findAll() {
		Map<String,Object> result=new HashMap<String,Object>();
		List<Map<String,Object>> hasil=new ArrayList<Map<String,Object>>();
		try {
			for(Map<String,Object> data:strukOrderDao.listStrukOrder()){
				if(CommonUtil.isNotNullOrEmpty(data.get("produk"))){
					result.put("data", modelMapper.map(data.get("produk"),ProdukVO.class ));
				}
				hasil.add(result);
			}
		} catch (Exception e) {
		
		}
		
		result.put("strukOrderList",hasil.get(0));
		return result;
	}


}
