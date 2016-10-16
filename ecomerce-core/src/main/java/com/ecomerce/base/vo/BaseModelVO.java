package com.ecomerce.base.vo;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.ecomerce.base.BaseMaster;
import com.ecomerce.base.BaseModel;
import com.ecomerce.base.BaseTransaction;
import com.ecomerce.util.CommonUtil;

/**
 * Base VO class for all entities, for exposing data as JSON Object
 * 
 * @author Roberto
 */
@MappedSuperclass
public abstract class BaseModelVO {

	public Map<String, Object> ToSingleMap() throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<String> fieldsToInclude = new ArrayList<String>();
		for (Field field : this.GetFields(this.getClass())) {
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
				maps.put(field.getName(), field.get(this));

		}
		return maps;
	}

	public Map<String, Object> ToMap() throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<String> fieldsToInclude = new ArrayList<String>();
		for (Field field : this.GetFields(this.getClass())) {
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
			if (valid == false && field.get(this)!=null)
				maps.put(field.getName(), field.get(this));

		}
		for (Field field : this.GetFields(this.getClass())) {
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
				if (valid == true) {
					Object property = field.get(this);

					if (property == null)
						{
						//maps.put(str, null);
						
						}
					else {
						if (property instanceof BaseModelVO) {
							Map<String, Object> mapItems = new HashMap<String, Object>();
							for (Field fieldItem : ((BaseModelVO) property).GetFields(property.getClass())) {
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
								for (java.lang.annotation.Annotation annotationItem : fieldItem
										.getDeclaredAnnotations()) {
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
								if (validItem == false && fieldItem.get(property)!=null) {
									mapItems.put(fieldItem.getName(), fieldItem.get(property));
								}
							}
							maps.put(str, mapItems);
						}
						else 
						 if(property instanceof BaseTransactionVO)
							{
							 Map<String, Object> mapItems = new HashMap<String, Object>();
								for (Field fieldItem : ((BaseTransactionVO) property).GetFields(property.getClass())) {
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
									for (java.lang.annotation.Annotation annotationItem : fieldItem
											.getDeclaredAnnotations()) {
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
									if (validItem == false && fieldItem.get(property)!=null) {
										mapItems.put(fieldItem.getName(), fieldItem.get(property));
									}
							}
							maps.put(str, mapItems);
						}

					}

				}
			}
		}

		return maps;
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
			tmpClass = BaseTransaction.class;
		} else if (BaseTransaction.class.isAssignableFrom(data.getClass())) {
			tmpClass = BaseMaster.class;
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

	public Map<String, Object> serialize(String[] fieldsToInclude, String className) throws Exception {
		//return ToMap();
		 String name = "com.ecomerce.medifirst2000.vo." + getName(className);
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
		 Map<String, Object> fields = new HashMap<String, Object>();
		 for (Object f : GetFields(cl).toArray()) {
			 if (f instanceof Field) {
				 ((AccessibleObject) f).setAccessible(true);
				 boolean found = false;
				 	if (CommonUtil.isNotNullOrEmpty(fieldsToInclude)) {
				 		for (String element : fieldsToInclude) {
				 			if (((Field) f).getName().equals(element))
				 			{
				 				found = true;
				 				break;
				 			}
				 		}
				 	} else {
				 		found = true;
				 	}

				 	if (found) {
				 		fields.put(((Field) f).getName(), ((Field) f).get(this));
				 	}
			 }
		 }
		 return fields;
	}

	public String getName(String value) {
		return value.substring(value.lastIndexOf('.') + 1).trim();
	}
}
