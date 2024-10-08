![image](https://github.com/user-attachments/assets/5f3186ca-66a8-4eb4-97e3-9df72ed8e175)

Umbrella Sharing Assitant
바쁜 일상 속, 일기예보를 제대로 확인하지 못하거나 또는 예보가 맞지 않아 갑작스레
비를 맞는 상황이 주변에서 종종 보이곤 합니다.
이러한 상황들로 인하여 사람들은 급하게 비닐우산을 구매하게 되고,
그 우산은 일회용성으로 사용 후 여기저기 버려지면서 매년 우산 쓰레기가 증가하고 있습니다.

저희는 위와 같은 문제점들의 해결과 함께 요즘 떠오르고 있는 공유 경제를 결합한
‘우산 공유 시스템’을 생각하게 되었고,
우산에 다양한 기능을 추가한 ‘스마트 우산 대여 서비스’를 개발하게 되었습니다.

이 프로젝트는 공유 경제의 실천과 함께 우산 쓰레기를 줄여 환경 보전에 보탬이 될 수 있고,
사용자들은 우산의 다양한 기능을 접할 수 있게 합니다.


시스템 구성도:

먼저 대여함에서는 바코드 스캐너 모듈과 RFID 태그를 통해 입력되는 값을 WIFI 연결을 통해 서버로 전송합니다.
  스마트 우산에서는 Bluetooth를 통해 우산의 위치와 해당 위치의 현재 강수량 값을 실시간으로 앱으로 전송하게 됩니다.
  앱에서는 우산에서 받아온 데이터를 서버로 전송하고, 서버에서 우산의 대여 가능 여부를 받아와 사용자에게 출력하여 줍니다.
  웹에서는 회원가입 및 문의 글에 대한 데이터를 서버로 보내고, 서버에서 대여소의 위치와 강수량, 회원정보 등을 받아와 보여줍니다.
  

앱의 구현 기능

순서대로 초기 로그인 화면과 홈 탭, 고장신고 탭, 이용안내 탭 화면입니다.

홈 탭에서 대여하기 버튼을 클릭하면 대여소 위치를 지도의 마커를 통해 확인 할 수 있으며,
마커를 클릭하면 토스트 창으로 위치와 우산의 대여 가능 여부가 표시됩니다.
QR코드 생성하기 버튼을 클릭하면 이용자의 id가 값으로 들어간 QR 코드를 생성할 수 있습니다.

홈 탭에서 블루투스 연결 버튼을 클릭하면 우산과 블루투스 연동을 할 수 있으며,
블루투스를 통해 현재 위치와 강수량 센서값을 확인 할 수 있습니다.
