package study.hard.javalib.commons.entity;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
public class UserHasInnerClass extends User {
	private static final long serialVersionUID = 6783342600937753674L;

	private DetailInfo detailInfo;

	public class DetailInfo extends BasisObject {
		private static final long serialVersionUID = -6313923221333376567L;

		private Float height;
		private Float weight;

		public DetailInfo() {
		}

		public DetailInfo(Float height, Float weight) {
			super();
			this.height = height;
			this.weight = weight;
		}

		public Float getHeight() {
			return height;
		}

		public void setHeight(Float height) {
			this.height = height;
		}

		public Float getWeight() {
			return weight;
		}

		public void setWeight(Float weight) {
			this.weight = weight;
		}

	}

	public UserHasInnerClass() {
	}

	public UserHasInnerClass(String name, Integer age) {
		super();
		setName(name);
		setAge(age);
	}

	public DetailInfo getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(DetailInfo detailInfo) {
		this.detailInfo = detailInfo;
	}

}
