/**
 * 
 */

function ceremony_map() {
    var mapOptions = {
        center: new google.maps.LatLng(38.6352, -90.2802),
        zoom: 16,
        mapTypeId: google.maps.MapTypeId.roadmap
    }
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);
}

function reception_map() {
    var mapOptions = {
        center: new google.maps.LatLng(38.6332, -90.2097),
        zoom: 16,
        mapTypeId: google.maps.MapTypeId.roadmap
    }
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);
}