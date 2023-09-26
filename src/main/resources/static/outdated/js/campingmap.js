async function getData() {
  const url = `https://apis.data.go.kr/B551011/GoCamping/locationBasedList?&MobileOS=etc&MobileApp=test&serviceKey=bsau4CZ2%2FCrD0c%2F%2BogGB12VyPtsUeBBOFUteULnfYcBpmDfH1O576AbDQDlmuutpWnVjQvGR%2BBU%2BEd5pbrERoA%3D%3D&numOfRows=10000&mapX=126.977012&mapY=37.5347189&radius=2000000&_type=json`;
  const response = await fetch(url);
  const data = await response.json();
  const locations = data.response.body.items.item.map(spot => [spot.facltNm, spot.mapY, spot.mapX, spot.lineIntro]);

  console.log("data", data);
  console.log("locations", locations);

  drawMap(locations);
}

function drawMap(locations) {
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 13,
    center: new google.maps.LatLng(37.5662952, 126.9779451),
    mapTypeId: google.maps.MapTypeId.ROADMAP,
  });

  const infowindow = new google.maps.InfoWindow();

  let marker, i;

  for (i = 0; i < locations.length; i++) {
    marker = new google.maps.Marker({
      position: new google.maps.LatLng(locations[i][1], locations[i][2]),
      map: map,
    });
  
    google.maps.event.addListener(marker, "click", (function(marker, i) {
      const infoContent = `<div><strong>${locations[i][0]}</strong><br>
                            ${locations[i][3]}<br>
                            <a href="https://www.google.com/maps/dir/?api=1&destination=${encodeURIComponent(locations[i][0])}&destination_place_id=${encodeURIComponent(locations[i][4])}&travelmode=transit" target="_blank">가는 방법 구글 지도로 확인하기</a></div>`;
      return function() {
        infowindow.setContent(infoContent);
        infowindow.open(map, marker);
      }
    })(marker, i));
  }
}

function initMap() {
  getData();
}
