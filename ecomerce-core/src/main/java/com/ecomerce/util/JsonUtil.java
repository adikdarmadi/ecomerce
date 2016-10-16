package com.ecomerce.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.base.BaseModel;
import com.google.gson.Gson;

public class JsonUtil {

	public static <T> T initializeAndUnproxy(T entity) {
		if (entity == null) {
			throw new NullPointerException("Entity passed for initialization is null");
		}

		Hibernate.initialize(entity);
		if (entity instanceof HibernateProxy) {
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}
	public static <T> List<Map<String, Object>> ToMaps(Set<T> model,Integer depth) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (Iterator iterator = model.iterator(); iterator.hasNext();) {
			T map = (T) iterator.next();
			Map<String, Object> convert = null;
			try {
				convert = ToMap(map,depth);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maps.add(convert);
		}
		return maps;
	}
	public static <T> List<Map<String, Object>> ToMaps(Set<T> model) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (Iterator iterator = model.iterator(); iterator.hasNext();) {
			T map = (T) iterator.next();
			Map<String, Object> convert = null;
			try {
				convert = ToMap(map);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maps.add(convert);
		}
		return maps;
	}
	@Transactional
	public static <T> List<Map<String, Object>> ToMaps(List<T> model,Integer depth)
			throws IllegalArgumentException, IllegalAccessException {

		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (Iterator iterator = model.iterator(); iterator.hasNext();) {
			T map = (T) iterator.next();
			Map<String, Object> convert = (Map<String, Object>) ToMapObject(map,depth);
			maps.add(convert);
		}
		return maps;
	}
	@Transactional
	public static <T> List<Map<String, Object>> ToMaps(List<T> model)
			throws IllegalArgumentException, IllegalAccessException {

		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (Iterator iterator = model.iterator(); iterator.hasNext();) {
			T map = (T) iterator.next();
			Map<String, Object> convert = (Map<String, Object>) ToMapObject(map);
			maps.add(convert);
		}
		return maps;
	}
	public static <T> Map<String, Object> ToMap(T model) throws IllegalArgumentException, IllegalAccessException {
		return ToMap(model,0);
	}

	public static <T> Map<String, Object> ToMap(T model,Integer depth) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<String> fieldsToInclude = new ArrayList<String>();
		if (model instanceof BaseModel)

			for (Field field : ((BaseModel) model).GetFields(model.getClass())) {
				String str = field.getName();
				if (str.equals("ruanganTujuan")) {
					System.out.println();
				}
				String name = field.getName();
				field.setAccessible(true);
				if (name.equals("serialVersionUID"))
					continue;
				if (name.equals("_methods_"))
					continue;
				if (name.equals("handler"))
					continue;
				if (name.equals("_filter_signature"))
					continue;
				Boolean valid = false;
				for (java.lang.annotation.Annotation annotation : field.getDeclaredAnnotations()) {
					if (annotation instanceof JoinColumn) {
						valid = true;
					} else if (annotation instanceof Column) {
						Column column = (Column) annotation;
						if (column.name().endsWith("Fk"))
							if (field.getName().endsWith("Id") == false)
								valid = true;
					} else if (annotation instanceof OneToMany) {

						valid = true;
					}

				}
				if (valid == false)
					maps.put(field.getName(), field.get(model));

			}
		if(depth>1)
		for (Field field : ((BaseModel) model).GetFields(model.getClass())) {
			String str = field.getName();
			String name = field.getName();
			field.setAccessible(true);
			if (str.equals("departemen")) {
				System.out.println();
			}
			if (name.equals("serialVersionUID"))
				continue;
			if (name.equals("_methods_"))
				continue;
			if (name.equals("handler"))
				continue;
			if (name.equals("_filter_signature"))
				continue;
			Boolean valid = false;
			for (java.lang.annotation.Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation instanceof JoinColumn) {
					valid = true;
				} else if (annotation instanceof Column) {
					Column column = (Column) annotation;
					if (column.name().endsWith("Fk"))
						if (field.getName().endsWith("Id") == false)
							valid = true;
				} else if (annotation instanceof OneToMany) {

					valid = true;
				}
			}
			if (valid == true) {
				Object property = field.get(model);

				if (property == null)
					maps.put(str, null);
				else {
					if (property instanceof Set) {
						List<Map<String, Object>> detailMaps = new ArrayList<Map<String, Object>>();
						
						for (Iterator iterator = ((Set)property).iterator(); iterator.hasNext();) {
							T map = (T) iterator.next();							
							Map<String, Object> convert = (Map<String, Object>) ToMapObject(map);
							detailMaps.add(convert);
						}
						
						maps.put(str, detailMaps);
					}else
					if (property instanceof BaseModel) {
						Map<String, Object> mapItems = new HashMap<String, Object>();
						for (Field fieldItem : ((BaseModel) property).GetFields(property.getClass())) {
							String strItem = fieldItem.getName();
							String nameItem = fieldItem.getName();
							fieldItem.setAccessible(true);
							if (nameItem.equals("serialVersionUID"))
								continue;
							if (nameItem.equals("_methods_"))
								continue;
							if (nameItem.equals("handler"))
								continue;
							if (nameItem.equals("_filter_signature"))
								continue;
							Boolean validItem = false;
							for (java.lang.annotation.Annotation annotationItem : fieldItem.getDeclaredAnnotations()) {
								if (annotationItem instanceof JoinColumn) {
									validItem = true;
								} else if (annotationItem instanceof Column) {
									Column columnItem = (Column) annotationItem;
									if (columnItem.name().endsWith("Fk"))
										if (fieldItem.getName().endsWith("Id") == false)
											validItem = true;
								} else if (annotationItem instanceof OneToMany) {

									validItem = true;
								}
							}
							if (validItem == false) {
								mapItems.put(fieldItem.getName(), fieldItem.get(property));
							}
						}
						for (Field fieldDetail : ((BaseModel) property).GetFields(property.getClass())) {
							String strDetail = fieldDetail.getName();
							String nameDetail = fieldDetail.getName();
							fieldDetail.setAccessible(true);
							if (nameDetail.equals("serialVersionUID"))
								continue;
							if (nameDetail.equals("_methods_"))
								continue;
							if (nameDetail.equals("handler"))
								continue;
							if (nameDetail.equals("_filter_signature"))
								continue;
							Boolean validDetail = false;
							for (java.lang.annotation.Annotation annotationDetail : fieldDetail
									.getDeclaredAnnotations()) {
								if (annotationDetail instanceof JoinColumn) {
									validDetail = true;
								} else if (annotationDetail instanceof Column) {
									Column column = (Column) annotationDetail;
									if (column.name().endsWith("Fk"))
										if (fieldDetail.getName().endsWith("Id") == false)
											validDetail = true;
								} else if (annotationDetail instanceof OneToMany) {

									validDetail = true;
								}
							}
							if (validDetail == true) {
								Object propertyDetail = fieldDetail.get(property);

								if (propertyDetail instanceof BaseModel) {
									Map<String, Object> mapDetailItems = new HashMap<String, Object>();
									for (Field fieldDetailItem : ((BaseModel) propertyDetail)
											.GetFields(propertyDetail.getClass())) {
										String strDetailItem = fieldDetailItem.getName();
										String nameDetailItem = fieldDetailItem.getName();
										fieldDetailItem.setAccessible(true);
										if (nameDetailItem.equals("serialVersionUID"))
											continue;
										if (nameDetailItem.equals("_methods_"))
											continue;
										if (nameDetailItem.equals("handler"))
											continue;
										if (nameDetailItem.equals("_filter_signature"))
											continue;
										Boolean validDetailItem = false;
										for (java.lang.annotation.Annotation annotationDetailItem : fieldDetailItem
												.getDeclaredAnnotations()) {
											if (annotationDetailItem instanceof JoinColumn) {
												validDetailItem = true;
											} else if (annotationDetailItem instanceof Column) {
												Column columnItem = (Column) annotationDetailItem;
												if (columnItem.name().endsWith("Fk"))
													if (fieldDetailItem.getName().endsWith("Id") == false)
														validDetailItem = true;
											} else if (annotationDetailItem instanceof OneToMany) {

												validDetailItem = true;
											}
										}
										if (validDetailItem == false) {
											mapDetailItems.put(fieldDetailItem.getName(),
													fieldDetailItem.get(propertyDetail));
										}
									}
									mapItems.put(strDetail, mapDetailItems);
								}

							}
						}
						maps.put(str, mapItems);
					}

				}

			}
		}

		return maps;
	}
	public static <T> Object ToMapObject(T model) throws IllegalArgumentException, IllegalAccessException {
		return ToMapObject(model,0);
	}
	public static <T> Object ToMapObject(T model,Integer depth) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<String> fieldsToInclude = new ArrayList<String>();
		if ((model instanceof Map)) {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> obj = (Map) model;
			for (String str : obj.keySet()) {
				Object array_element = obj.get(str);
				if (array_element instanceof BaseModel) {
					
					result.put(str, ((BaseModel)array_element).ToMap());
				}
				else
					result.put(str,array_element);
			}
		
			return (Map) result;
		}

		for (Field field : ((BaseModel) model).GetFields(model.getClass())) {
			String str = field.getName();

			String name = field.getName();
			field.setAccessible(true);
			if (name.equals("serialVersionUID"))
				continue;
			if (name.equals("_methods_"))
				continue;
			if (name.equals("handler"))
				continue;
			if (name.equals("_filter_signature"))
				continue;
			Boolean valid = false;
			for (java.lang.annotation.Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation instanceof JoinColumn) {
					valid = true;
				} else if (annotation instanceof Column) {
					Column column = (Column) annotation;
					if (column.name().endsWith("Fk"))
						if (field.getName().endsWith("Id") == false)
							valid = true;
				} else if (annotation instanceof OneToMany) {

					valid = true;
				}

			}
			if (valid == false)
				maps.put(field.getName(), field.get(model));

		}
		if(depth ==0)
		for (Field field : ((BaseModel) model).GetFields(model.getClass())) {
			String str = field.getName();
			String name = field.getName();
			field.setAccessible(true);
			if (str.equals("departemen")) {
				System.out.println();
			}
			if (name.equals("serialVersionUID"))
				continue;
			if (name.equals("_methods_"))
				continue;
			if (name.equals("handler"))
				continue;
			if (name.equals("_filter_signature"))
				continue;
			Boolean valid = false;
			for (java.lang.annotation.Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation instanceof JoinColumn) {
					valid = true;
				} else if (annotation instanceof Column) {
					Column column = (Column) annotation;
					if (column.name().endsWith("Fk"))
						if (field.getName().endsWith("Id") == false)
							valid = true;
				} else if (annotation instanceof OneToMany) {

					valid = true;
				}
			}
			if (valid == true) {
				Object property = field.get(model);
				if (property instanceof HibernateProxy) {
					property = initializeAndUnproxy(property);
				}
				if (property == null)
					maps.put(str, null);
				else {
					if (property instanceof Set) {
						List<Map<String, Object>> mapsChild = new ArrayList<Map<String, Object>>();
						for (Iterator iterator = ((Set) property).iterator(); iterator.hasNext();) {
							T map = (T) iterator.next();
							Map<String, Object> convert = (Map<String, Object>) ToMapObject(map);
							mapsChild.add(convert);
						}
						maps.put(str, mapsChild);
					}
					if (property instanceof BaseModel) {
						Map<String, Object> mapItems = new HashMap<String, Object>();
						for (Field fieldItem : ((BaseModel) property).GetFields(property.getClass())) {
							String strItem = fieldItem.getName();
							String nameItem = fieldItem.getName();
							fieldItem.setAccessible(true);
							if (nameItem.equals("serialVersionUID"))
								continue;
							if (nameItem.equals("_methods_"))
								continue;
							if (nameItem.equals("handler"))
								continue;
							if (nameItem.equals("_filter_signature"))
								continue;
							Boolean validItem = false;
							for (java.lang.annotation.Annotation annotationItem : fieldItem.getDeclaredAnnotations()) {
								if (annotationItem instanceof JoinColumn) {
									validItem = true;
								} else if (annotationItem instanceof Column) {
									Column columnItem = (Column) annotationItem;
									if (columnItem.name().endsWith("Fk"))
										if (fieldItem.getName().endsWith("Id") == false)
											validItem = true;
								} else if (annotationItem instanceof OneToMany) {

									validItem = true;
								}
							}
							if (validItem == false) {
								mapItems.put(fieldItem.getName(), fieldItem.get(property));
							}
						}
						for (Field fieldDetail : ((BaseModel) property).GetFields(property.getClass())) {
							String strDetail = fieldDetail.getName();
							String nameDetail = fieldDetail.getName();
							fieldDetail.setAccessible(true);
							if (nameDetail.equals("serialVersionUID"))
								continue;
							if (nameDetail.equals("_methods_"))
								continue;
							if (nameDetail.equals("handler"))
								continue;
							if (nameDetail.equals("_filter_signature"))
								continue;
							Boolean validDetail = false;
							for (java.lang.annotation.Annotation annotationDetail : fieldDetail
									.getDeclaredAnnotations()) {
								if (annotationDetail instanceof JoinColumn) {
									validDetail = true;
								} else if (annotationDetail instanceof Column) {
									Column column = (Column) annotationDetail;
									if (column.name().endsWith("Fk"))
										if (fieldDetail.getName().endsWith("Id") == false)
											validDetail = true;
								} else if (annotationDetail instanceof OneToMany) {

									validDetail = true;
								}
							}
							if (validDetail == true) {
								Object propertyDetail = fieldDetail.get(property);
								if (propertyDetail instanceof HibernateProxy) {
									propertyDetail = initializeAndUnproxy(propertyDetail);
								}
								if (propertyDetail instanceof BaseModel) {
									Map<String, Object> mapDetailItems = new HashMap<String, Object>();
									mapDetailItems = ((BaseModel) propertyDetail).ToMap();
//									for (Field fieldDetailItem : ((BaseModel) propertyDetail)
//											.GetFields(propertyDetail.getClass())) {
//										String strDetailItem = fieldDetailItem.getName();
//										String nameDetailItem = fieldDetailItem.getName();
//										fieldDetailItem.setAccessible(true);
//										if (nameDetailItem.equals("serialVersionUID"))
//											continue;
//										if (nameDetailItem.equals("_methods_"))
//											continue;
//										if (nameDetailItem.equals("handler"))
//											continue;
//										if (nameDetailItem.equals("_filter_signature"))
//											continue;
//										Boolean validDetailItem = false;
//										for (java.lang.annotation.Annotation annotationDetailItem : fieldDetailItem
//												.getDeclaredAnnotations()) {
//											if (annotationDetailItem instanceof JoinColumn) {
//												validDetailItem = true;
//											} else if (annotationDetailItem instanceof Column) {
//												Column columnItem = (Column) annotationDetailItem;
//												if (columnItem.name().endsWith("Fk"))
//													if (fieldDetailItem.getName().endsWith("Id") == false)
//														validDetailItem = true;
//											} else if (annotationDetailItem instanceof OneToMany) {
//
//												validDetailItem = true;
//											}
//										}
//										if (validDetailItem == false) {
//											mapDetailItems.put(fieldDetailItem.getName(),
//													fieldDetailItem.get(propertyDetail));
//										}
//									}
									mapItems.put(strDetail, mapDetailItems);
								}

							}
						}
						maps.put(str, mapItems);
					}

				}

			}
		}

		return maps;
	}

	public static Map<String, Object> Convert(String jsonText) {
		Gson gson = new Gson();
		String json = "";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "mkyong");
		map.put("age", 29);

		// convert map to JSON string
		map = gson.fromJson(jsonText, map.getClass());

		System.out.println(json);

		// json =
		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

		// pretty print
		System.out.println(json);
		return map;
	}

	public static <T> T Convert(String string, T vo) {
		Gson gson = new Gson();
		// TODO Auto-generated method stub
		return (T) gson.fromJson(string, vo.getClass());

	}

}
