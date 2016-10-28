package com.jasamedika.medifirst2000.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jasamedika.medifirst2000.vo.StrukOrderVO;
import com.jasamedika.medifirst2000.vo.StrukResepVO;

import io.socket.client.IO;

import com.jasamedika.medifirst2000.entities.StrukOrder;
import com.jasamedika.medifirst2000.entities.StrukResep;

import com.jasamedika.medifirst2000.exception.ServiceVOException;
import com.jasamedika.medifirst2000.service.ActivityPegawaiService;
import com.jasamedika.medifirst2000.service.PelayananObatService;
import com.jasamedika.medifirst2000.service.StokProdukGlobalService;
import com.jasamedika.medifirst2000.util.CommonUtil;
import com.jasamedika.medifirst2000.util.ExtendedSpringBeanUtil;
import com.jasamedika.medifirst2000.util.JsonUtil;
import com.jasamedika.medifirst2000.util.StringUtil;
import com.jasamedika.medifirst2000.entities.ActivityPegawai;
import com.jasamedika.medifirst2000.entities.JenisObat;
import com.jasamedika.medifirst2000.entities.KartuStok;
import com.jasamedika.medifirst2000.entities.KomponenHarga;
import com.jasamedika.medifirst2000.entities.Pasien;
import com.jasamedika.medifirst2000.entities.PasienDaftar;
import com.jasamedika.medifirst2000.entities.Pegawai;
import com.jasamedika.medifirst2000.entities.PelayananObat;
import com.jasamedika.medifirst2000.entities.PelayananPasien;
import com.jasamedika.medifirst2000.entities.PelayananPasienDetail;
import com.jasamedika.medifirst2000.entities.Produk;
import com.jasamedika.medifirst2000.entities.Ruangan;
import com.jasamedika.medifirst2000.entities.StokProdukGlobal;
import com.jasamedika.medifirst2000.vo.JenisObatVO;
import com.jasamedika.medifirst2000.vo.PegawaiVO;
import com.jasamedika.medifirst2000.vo.PelayananObatVO;
import com.jasamedika.medifirst2000.vo.PelayananPasienVO;
import com.jasamedika.medifirst2000.vo.ProdukVO;
import com.google.gson.Gson;
import com.jasamedika.medifirst2000.base.BaseModel;
import com.jasamedika.medifirst2000.converter.BaseConverterImpl;
import com.jasamedika.medifirst2000.converter.PasienConverter;
import com.jasamedika.medifirst2000.converter.PegawaiConverter;
import com.jasamedika.medifirst2000.dao.ActivityPegawaiDao;
import com.jasamedika.medifirst2000.dao.KartuStokDao;
import com.jasamedika.medifirst2000.dao.PasienDaftarDao;
import com.jasamedika.medifirst2000.dao.PasienDao;
import com.jasamedika.medifirst2000.dao.PegawaiDao;
import com.jasamedika.medifirst2000.dao.PelayananObatDao;
import com.jasamedika.medifirst2000.dao.PelayananPasienDao;
import com.jasamedika.medifirst2000.dao.PelayananPasienDetailDao;
import com.jasamedika.medifirst2000.dao.StokProdukGlobalDao;
import com.jasamedika.medifirst2000.dao.StrukOrderDao;
import com.jasamedika.medifirst2000.dao.StrukResepDao;

/**
 * Implement class for PelayananObatService
 * 
 * @author Generator
 * @param <T>
 */
@Service("activityPegawaiServiceImpl")
public class ActivityPegawaiServiceImpl  implements ActivityPegawaiService {
	static io.socket.client.Socket socket =null;
	@PersistenceContext
	protected EntityManager em;
	
	
	public String GetSettingDataFixed(String prefix) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select model.nilaiField from SettingDataFixed ")
				.append(" model  where model.namaField ='"+prefix+"' ");
		Query query = em.createQuery(buffer.toString());

		return (String)query.getSingleResult();
	}
	protected void BroadcastMessage(final String to, final Map data) {
		try {
			if(socket==null)
			{
				socket = IO.socket(GetSettingDataFixed("UrlSocketMessaging"));
	
				socket.on(io.socket.client.Socket.EVENT_CONNECT, new io.socket.emitter.Emitter.Listener() {
	
					@Override
					public void call(Object... args) {
						
						try {
							Gson gson = new Gson();
							JSONObject item=	new JSONObject("{\"to\":\""+to+"\",\"message\":"+gson.toJson(data)+"}");
							socket.emit("subscribe", item);
							//socket.disconnect();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
	
				});
				socket.connect();
			}
			else{
				try {
					Gson gson = new Gson();
					JSONObject item=	new JSONObject("{\"to\":\""+to+"\",\"message\":"+gson.toJson(data)+"}");
					socket.emit("subscribe", item);
					//socket.disconnect();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Autowired
	private BaseConverterImpl<PegawaiVO, Pegawai> pelayananObatConverter;
	@Autowired
	private ActivityPegawaiDao activityPegawaiDao;
	@Autowired
	private PegawaiDao pegawaiDao;
	
	@Override
	@Transactional(readOnly=false)
	public Boolean record(PegawaiVO pegawai, Date tanggal, String keterangan) {
		Pegawai pegawaiSave = new Pegawai();
		pegawaiSave.setId(pegawai.getId());
		ActivityPegawai act = new ActivityPegawai();
		act.setKeterangan(keterangan);
		act.setPegawai(pegawaiSave);
		act.setTanggalKejadian(new  Date());
		
		act =activityPegawaiDao.save(act);
		act =activityPegawaiDao.findOne(act.getNoRec());
		act.setPegawai(pegawaiDao.findById(pegawai.getId()));
		try {
			BroadcastMessage("IKI",act.ToMap());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return act.getNoRec()!="";
	}
	@Override
	@Transactional(readOnly=false)
	public Boolean record(PegawaiVO pegawai, Date tanggal, String keterangan,String group) {
		Pegawai pegawaiSave = new Pegawai();
		pegawaiSave.setId(pegawai.getId());
		ActivityPegawai act = new ActivityPegawai();
		act.setKeterangan(keterangan);
		act.setGroup(group);
		act.setPegawai(pegawaiSave);
		act.setTanggalKejadian(new  Date());
		
		act =activityPegawaiDao.save(act);
		act =activityPegawaiDao.findOne(act.getNoRec());
		act.setPegawai(pegawaiDao.findById(pegawai.getId()));
		try {
			BroadcastMessage("IKI",act.ToMap());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return act.getNoRec()!="";
	}
	@Override
	public List<ActivityPegawai> getData() {
		// TODO Auto-generated method stub
		return (List<ActivityPegawai>) activityPegawaiDao.findDescending();
	}
	@Override
	public List<ActivityPegawai> getData(Date start, Date until,Integer top) {
		// TODO Auto-generated method stub
		return (List<ActivityPegawai>) activityPegawaiDao.findDescending(start,until,new PageRequest(0,top));
	}
}