import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import getIpData from "./api.js";
import { ipAddressValue, ipInput, locationValue, searchButton, timezoneValue, ispValue, mapArea, mapIcon } from "./views.js";

document.addEventListener('DOMContentLoaded', onInitApplication)

searchButton.addEventListener('click', onSearchButtonClicked)
ipInput.addEventListener('keydown', onIpInputKeyDown)

function onInitApplication() {
    initMap()
}

async function onSearchButtonClicked(event) {
    const input = ipInput.value
    const ipData = await getIpData(input)
    ipDataReceived(ipData)
}

async function onIpInputKeyDown(event) {
    if (event.key !== 'Enter') {
        return
    }

    const input = ipInput.value
    const ipData = await getIpData(input)
    ipDataReceived(ipData)
}

function ipDataReceived(ipData) {
    if (ipData === null) {
        return
    }

    const location = ipData.location
    ipAddressValue.innerText = ipData.ip
    locationValue.innerText = `${location.country} ${location.region} ${location.city}`
    timezoneValue.innerText = location.timezone
    ispValue.innerText = ipData.isp

    L.map(mapArea, {
        center: [location.lat, location.lng]
    })
}

function initMap() {
    L.map(mapArea, {
        center: [51.505, -0.09],
        zoom: 13
    })
    L.tileLayer(
        'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}/', {
            attribution: `   Challenge by <a href="https://www.frontendmentor.io?ref=challenge" target="_blank">Frontend Mentor</a>. 
            Coded by <a href="#">Ferum-bot</a>.`,
            maxZoom: 18,
            id: 'mapbox/streets-v11',
            tileSize: 512,
            zoomOffset: -1,
            accessToken: 'Your Access Token'
        }
    ).addTo(mapArea)
    L.marker([51.505, -0.09], { icon: mapIcon }).addTo(mapArea)
}