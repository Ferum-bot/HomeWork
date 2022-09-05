import getIpData from "./api.js";
import { ipAddressValue, ipInput, locationValue, searchButton, timezoneValue, ispValue } from "./views.js";

searchButton.addEventListener('click', onSearchButtonClicked)
ipInput.addEventListener('keydown', onIpInputKeyDown)

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
}