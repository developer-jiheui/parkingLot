package parking;

import java.util.*;

public class ParkingLot {

    /* field 영역 수정 금지 */
    private String name;
    private List<Car> cars;
    private Scanner sc;
    public final static int LIMIT = 5;

    //Constructor of ParkingLot
    public ParkingLot(String name) {
        this.name = name;
        //initializing ArrayList Car with max number of cars
        this.cars = new ArrayList<Car>(LIMIT);
    }

    //차를 주차장에 추가하는 메소드.
    // Car 객체를 매개변수로 받아서 그 객체를 주차장에 추가할수 있는지 확인 후 추가한다.
    public void addCar(Car car) throws RuntimeException {
        //차가 5대 이상이면 만차
        if (cars.size() >= 5) {
            System.out.println("만차입니다. 더 이상 차량 등록이 불가능합니다.\n");
        }//차가 5대 미만이면
        else {
            //car객체를 cars에 더한다
            this.cars.add(car);
            System.out.println("차량번호 " + car.getCarNo() + " 차량이 등록되었습니다");
        }
    }

    //차를 목록에서 삭제하는 메소드
    //modelNum를 변수로 받아서 searchCar()로 차량이 있는지 확인 후 삭제한다.
    public Car deleteCar(String modelNum) throws RuntimeException {
        //삭제할 차량의 인덱스를 searchCar() 메소드의 반환값으로 받는다.
        int removeIndex = searchCar(modelNum);

        System.out.print("차량번호 " + modelNum + " 차량이");

        //인덱스가 0이상이면 차량을 찾은것이므로, 해당 인덱스 값을 사용해 차를 삭제
        if (removeIndex >= 0) {
            System.out.print(" 삭제되었습니다\n");
            //삭제된 차량의 객체를 반환한다
            return cars.remove(removeIndex);
        } else {
            //삭제된 차량이 없으면 null을 반환한다
            return null;
        }
    }

    //차가 cars에 있는지 찾는 메소드. 찾은 차의 인덱스를 반환한다.
    public int searchCar(String modelNum) throws RuntimeException {

        //modelNum을 받아서 cars에 차량번호가 같은 차량이 있는지 확인한다
        for (int i = 0; i < cars.size(); i++) {
            if (modelNum.equals(cars.get(i).getCarNo())) {
                System.out.println("조회결과 Car [carNo=" + cars.get(i).getCarNo() + ", model=" + cars.get(i).getModel() + "]");
                //차를 찾는데 성공하면 해당 인덱스를 반환한다.
                return i;
            }
        }
        //차량을 모두 조회한 후에도 매치되는 결과가 없다면, 차가 없다는 뜻이므로 -1을 반환한다.
        return -1;
    }

    //전체 차량 목록을 조회하는 메소드
    public void printAllCars() throws RuntimeException {
        for (int i = 0; i < cars.size(); i++) {
            System.out.print((i + 1) + "번째 차량: ");
            //해당 인덱스에 있는 객체가 null인지 확인 후, 아니라면 객체정보를 프린트한다
            if (cars.get(i) == null) {
                System.out.print("없습니다");
            } else {
                System.out.println("Car [carNo=" + cars.get(i).getCarNo() + ", model=" + cars.get(i).getModel() + "]");
            }
        }
    }

    //유저가 입력한 인풋을 처리하는 메소드
    public void manage() {
        //input을 받기 위해 Scanner 객체를 사용한다.
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        //loop이 돌아갈 조건의 -1을 지정한다.
        int userInputInt = -1;

        //유저가 입력한 내용이 0이 아닐경우 계속 시행된다
        while (userInputInt != 0) {

            System.out.print("1.추가 2.삭제 3.조회 4.전체조회 0.종료 >>> ");
            userInput = scanner.next();
            //입력한 내용을 userInput에 저장하고 checkInt()메소드를 사용해 int값으로 변환할수 있는지 확인한다.
            if (checkInt(userInput)) {
                //변환가능한 인풋이라면 변환해준다.
                userInputInt = Integer.parseInt(userInput);

                //0-4 중 선택한 값에 따라 다음 행동을 한다
                switch (userInputInt) {
                    //추가
                    case 1:
                        //추가할 차량 객체를 생성한다
                        Car car = new Car();

                        System.out.print("현재 등록된 차량 : " + cars.size() + "대\n");

                        //car의 수가 5개 이하일때만 차량번호와 모델번호 입력을 요청한다
                        if (cars.size() < 5) {
                            System.out.print("차량번호 입력 >>> ");
                            car.setCarNo(scanner.next());

                            System.out.print("모델 입력 >>>");
                            car.setModel(scanner.next());
                        }

                        //차를 추가할 수 있어서 입력내용을 받았다면 그것을 cars에 더해준다.
                        //주차장이 꽉 찼다면 유저에게 알려준다
                        addCar(car);
                        break;

                    //삭제
                    case 2:
                        //cars에 등록된 차량이 있는지 먼저 확인후 없으면 알려준다.
                        if (cars.size() == 0) {
                            System.out.println("현재 주차장에 등록된 차량이 없습니다.\n");
                        }
                        else { //등록된 차가 있으면 삭제할 차량번호를 묻는다
                            System.out.print("삭제할 차량번호 입력 >>> ");

                            //deleteCar()에 유저에게 받은 입력내용(String 차량번호)를 보낸 후
                            //반환 객체가 null 이라면 차량이 없는 것이므로 없다고 알려준다
                            //찾은 경우 deleteCar()안에서 삭제 통보후 삭제한다.
                            if (deleteCar(scanner.next()) == null) {
                                System.out.print(" 없습니다\n");
                            }
                        }
                        break;

                    //조회
                    case 3:
                        System.out.print("조회할 차량번호 입력 >>> ");
                        //searchCar(String 차량번호)에 입력내용을 보내준 후, 인덱스 값을 받는다
                        //찾지 못했다면 -1일 것이므로 차량번호에 해당하는 차량이 없다고 알려준다.
                        if (searchCar(scanner.next()) < 0) {
                            System.out.println("차량번호 " + scanner.next() + "차량이 존재하지 않습니다.\n");
                        }
                        //찾은 경우에는 searchCar()안에서 찾은 차량의 정보를 프린트한다.
                        break;

                    //전체조회
                    case 4:
                        //cars의 크기가 양수가 아닐경우 등록된 차량이 없다.
                        if (cars.size() <= 0) {
                            System.out.println("현재 주차장에 등록된 차량이 없습니다.\n");
                        } else {//등록된 차량이 있다면 모두 조회해 프린트해준다.
                            printAllCars();
                        }
                        break;
                    //종료
                    default:
                        System.out.println("주차장을 종료합니다");
                        //userInputInt를 0으로 설정하여 while loop에서 빠져나간다.
                        userInputInt = 0;
                        break;
                }
            }
        }
    }

    //NumberFormatException을 피하기 위해 유저가 0-4의 숫자만 입력했는지 try catch로 확인한다.
    public boolean checkInt(String input) {
        try {
            int isRightNum = Integer.parseInt(input);
            if (isRightNum >= 5) {
                System.out.println("숫자 0,1,2,3,4 중에서 선택해 입력하세요");
            }
            return Integer.parseInt(input) < 5;
        } catch (Exception e) {
            System.out.println("숫자만 입력하세요");
        }
        return false;
    }


}
