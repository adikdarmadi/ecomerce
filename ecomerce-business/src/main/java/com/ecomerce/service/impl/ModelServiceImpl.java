package com.ecomerce.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Id;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.IteratorUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.base.BaseModel;
import com.ecomerce.base.vo.BaseMasterVO;
import com.ecomerce.base.vo.BaseModelVO;
import com.ecomerce.base.vo.BaseTransactionVO;
import com.ecomerce.dao.custom.GenericServDao;
import com.ecomerce.helper.Caption;
import com.ecomerce.service.ModelService;
import com.ecomerce.util.CommonUtil;
import com.ecomerce.util.JsonUtil;
import com.ecomerce.vo.ModelVO;

/**
 * Implement class for PasienService
 * 
 * @author Adik
 * @param <T>
 * @param <T>
 */
@Service
public class ModelServiceImpl<T> implements ModelService<T> {
	@Autowired
	private GenericServDao<T> genericServDao;
	
	@Autowired
	private MessageSource messageSource;

	// get /convert string name from org.hibernate.validator.constraints.Length
	// to length
	public String getName(String value) {
		return value.substring(value.lastIndexOf('.') + 1).trim();
	}

	public Object converToObject(String value) {
		String name = "com.ecomerce.medifirst2000.vo." + value;
		Class cl = null;
		Object o = null;
		try {
			cl = Class.forName(name);
			o = cl.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return o;
	}

	public static List<Field> GetFields(Class data) {
		List<Field> items = new ArrayList<Field>();
		String name = data.getName();
		Class parent = data.getSuperclass();
		if (parent instanceof Class) {
			name = ((Class) parent).getName();
		}

		Class tmpClass = null;
		if (BaseModelVO.class.isAssignableFrom(data.getClass())) {
			tmpClass = BaseTransactionVO.class;
		} else if (BaseTransactionVO.class.isAssignableFrom(data.getClass())) {
			tmpClass = BaseMasterVO.class;
		}

		if ((data == tmpClass)) {
			for (Field field : data.getDeclaredFields()) {
				items.add(field);
			}
			return items;
		}

		if (parent != null) {
			for (Field field : GetFields(data.getSuperclass())) {
				items.add(field);
			}
		}

		for (Field field : data.getDeclaredFields()) {
			items.add(field);
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public ModelVO getModelSerializeEntity(String name,String language) {

		ModelVO modelDTO = new ModelVO();
		List<Map<String,Object>> allAttributes = new ArrayList<Map<String, Object>>();
		Object o = converToObject(name);
		Map<String, Object> model = new HashMap<String, Object>();
		for (Object field : GetFields(o.getClass()).toArray()) {
			if (field instanceof Field){

				String[] fieldsToInclude = { "String", "Integer", "int","byte","short","Boolean","Byte","Date","Double","double","long","Long","Float","float"};
				boolean found = false;
				for (String element : fieldsToInclude) {
				    if (getName(((Field) field).getType().toString()).equals(element)) {
				        found = true;
				        break;
				    }
				}
				if (!found) {
					
					if (getName(((Field) field).getType().toString()).equals("Set")) {
						model.put(((Field) field).getName(), new ArrayList<Object>());
					}else{
						model.put(((Field) field).getName(), new BaseModel() {
						});
					}
					
				}else{
					model.put(((Field) field).getName(), "");
				}

			Annotation[] as = ((Field) field).getDeclaredAnnotations();
			
			if(CommonUtil.isNotNullOrEmpty(as)){
				for (Annotation a : as) {
					if (a instanceof javax.persistence.ManyToOne) {
						Map<String, Object> modelChild = new HashMap<String, Object>();
						for (Object fieldChild : GetFields(converToObject(getName(((Field) field).getType().toString())).getClass()).toArray()) {
							boolean foundChild = false;
							for (String element : fieldsToInclude) {
							    if (getName(((Field) fieldChild).getType().toString()).equals(element)) {
							    	foundChild = true;
							        break;
							    }
							}
							if (!foundChild) {
								
								if (getName(((Field) fieldChild).getType().toString()).equals("Set")) {
									modelChild.put(((Field) fieldChild).getName(), new ArrayList<Object>());
								}else{
									modelChild.put(((Field) fieldChild).getName(), new BaseModel() {
									});
								}
								
							}else{
								modelChild.put(((Field) fieldChild).getName(), "");
							}

							
						}
						List<Map<String,Object>> attribtues =listMapAttributes(converToObject(getName(((Field) field).getType().toString())),((Field) field).getName(),language);
						for(Map<String,Object> map : attribtues)
						{
							allAttributes.add(map);
						}
						//modelChild.put("attributes", listMapAttributes(converToObject(getName(((Field) field).getType().toString()))));
						model.put(((Field) field).getName(), modelChild);
					}

				}

			}
			}
		}

		modelDTO.setModel(model);
		for(Map<String,Object> map : listMapAttributes(o,"",language))
		{
			allAttributes.add(map);
		}
		modelDTO.setAttributes(allAttributes);
		return modelDTO;

	}

	
	
	
	//generate attribute json
	public List<Map<String, Object>>listMapAttributes(Object object,String modelName,String language){

		List<Map<String, Object>> attributeModelList = new ArrayList<Map<String, Object>>();

		for (Object field : GetFields(object.getClass()).toArray()) {
			if (field instanceof Field) {
				Map<String, Object> attributeModel = new HashMap<String, Object>();
				if(modelName != "")
					attributeModel.put("name",modelName+"."+ ((Field) field).getName());
				else
					attributeModel.put("name", ((Field) field).getName());
				attributeModel.put("type", getName(((Field) field).getType().getName()));

				Annotation[] as = ((Field) field).getDeclaredAnnotations();
				
				for (Annotation a : as) {
					checkObject(a, attributeModel,language);
				}
				attributeModelList.add(attributeModel);
			}
		}
		return attributeModelList;
	}
		
	public  Map<String, Object> checkObject(Annotation a,Map<String, Object> attributeModel, String language){
		if (a instanceof org.hibernate.validator.constraints.Length) {
			org.hibernate.validator.constraints.Length length = (Length) a;
			attributeModel.put("maxlength", length.max());
			attributeModel.put("minlength", length.min());
			attributeModel.put("messagesRequired", length.message());
		}

		if (a instanceof javax.persistence.Column) {
			javax.persistence.Column attribute = (javax.persistence.Column) a;

			attributeModel.put("isNull", attribute.nullable());
			attributeModel.put("length", attribute.length());

		}

		if (a instanceof javax.validation.constraints.NotNull) {
			javax.validation.constraints.NotNull notNull = (javax.validation.constraints.NotNull) a;
			attributeModel.put("isNull", false);
			attributeModel.put("messagesRequired", notNull.message());
		}

		if (a instanceof com.ecomerce.helper.Caption) {
			com.ecomerce.helper.Caption test = (Caption) a;
			System.out.println(test.value());
			//attributeModel.put("caption", messageSource.getMessage(test.value(), null, new Locale(language)));
			attributeModel.put("caption",test.value());
		}
	
		return attributeModel;
		
		
		
		
	}

	@Override
	@Transactional
	public List<Map<String,Object>> getAllData(String entity, String field, Integer take, Integer skip, Integer page,
			Integer pageSize, String logic, String value, String fieldS, String operator, String ignorecase,
			String criteria, String values) {
		int rowStart = 0;
		int rowEnd = 0;
		if(take!=null)
			rowEnd=take;
		if (take != null && skip != null && page != null && pageSize != null) {
			int totalRow = genericServDao.dataCount(entity, value, fieldS, criteria, values);
			int totalPages = 0;

			int pageRequested = page;

			if (totalRow > 0) {
				totalPages = (int) Math.ceil((double) totalRow / (double) pageSize);
			} else {
				totalPages = 0;
			}

			if (pageRequested > totalPages)
				pageRequested = totalPages;
			rowStart = (pageRequested * pageSize) - pageSize;
			rowEnd = take;
		}
		List<Map<String, Object>> listEntity =null;
		try {
			listEntity = JsonUtil.ToMaps( genericServDao.getDatas(entity, field, rowStart, rowEnd, logic, value, fieldS,
					operator,criteria,values));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listEntity;
	}


}
