import { validateIp } from "./helpers.js"

export default async function getIpData(ip) {
    if (!validateIp(ip)) {
        return null
    }

    const url = `https://geo.ipify.org/api/v2/country,city?apiKey=at_YO8P99R4z0EDFqqqJLTYW3ca4nTSM&ipAddress=${ip}`
    const response = await fetch(url)
    return await response.json()
}