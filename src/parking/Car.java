package parking;

/* 코드 수정 금지 */

public class Car {

  private String carNo;
  private String model;
  
  public Car() {
    
  }
  public Car(String carNo, String model) {
    super();
    this.carNo = carNo;
    this.model = model;
  }
  
  public String getCarNo() {
    return carNo;
  }
  public void setCarNo(String carNo) {
    this.carNo = carNo;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return "Car [carNo=" + carNo + ", model=" + model + "]";
  }
  
}
