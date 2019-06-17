package org.dppc.vv.common.model;

/**
 * @描述 :  分页展示
 * @作者 :	DongZhaoYang
 * @日期 :	2017/10/16
 * @时间 :	15:57
 */
public class PageDTO {
	private Integer code;//状态码
	private String msg;//状态信息，一般可为空
	private Long count;//总条数
	private Object data;//列表

	public PageDTO() {
	}

	public PageDTO(Integer code, String msg, Long count, Object data) {
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public PageDTO(Long count, Object data) {
		this.code = CodeType.SUCCESS.value;
		this.msg = CodeType.SUCCESS.name;
		this.count = count;
		this.data = data;
	}



	public enum  CodeType {

		SUCCESS("成功", 0)
		, FAIL("失败", 1);

		private String name;
		private int value;

		CodeType(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public int getValue() {
			return value;
		}
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
