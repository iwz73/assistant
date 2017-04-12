package idv.hsiehpinghan.springmvcassistant.criteria;

import java.util.List;

public class SpringMvcFormCriteria {
	private String input;
	private String password;
	private String textarea;
	private boolean singleCheckbox;
	private String[] multiCheckboxs;
	private List<String> checkboxs;
	private String radiobutton;
	private String radiobuttons;
	private String select;
	private String options;
	private String hidden;
	private List<SpringMvcFormCriteriaObject> objects;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTextarea() {
		return textarea;
	}

	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}

	public boolean isSingleCheckbox() {
		return singleCheckbox;
	}

	public void setSingleCheckbox(boolean singleCheckbox) {
		this.singleCheckbox = singleCheckbox;
	}

	public String[] getMultiCheckboxs() {
		return multiCheckboxs;
	}

	public void setMultiCheckboxs(String[] multiCheckboxs) {
		this.multiCheckboxs = multiCheckboxs;
	}

	public List<String> getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(List<String> checkboxs) {
		this.checkboxs = checkboxs;
	}

	public String getRadiobutton() {
		return radiobutton;
	}

	public void setRadiobutton(String radiobutton) {
		this.radiobutton = radiobutton;
	}

	public String getRadiobuttons() {
		return radiobuttons;
	}

	public void setRadiobuttons(String radiobuttons) {
		this.radiobuttons = radiobuttons;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public List<SpringMvcFormCriteriaObject> getObjects() {
		return objects;
	}

	public void setObjects(List<SpringMvcFormCriteriaObject> objects) {
		this.objects = objects;
	}

	public static class SpringMvcFormCriteriaObject {
		private String id;
		private String name;
		private int age;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "SpringMvcFormCriteriaObject [id=" + id + ", name=" + name + ", age=" + age + "]";
		}

	}
}
