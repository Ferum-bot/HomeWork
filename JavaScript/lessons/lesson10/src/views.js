import L from 'leaflet';
import mapIconPng from '../images/icon-location.svg';

export const ipInput = document.querySelector('.search-bar__input')
export const searchButton = document.querySelector('.search-bar__btn')

export const ipAddressValue = document.querySelector('#ip')
export const locationValue = document.querySelector('#location')
export const timezoneValue = document.querySelector('#timezone')
export const ispValue = document.querySelector('#isp')

export const mapArea = document.querySelector('.map')
export const mapIcon = L.icon({
    iconUrl: mapIconPng,
    iconSize:  [30, 40],
    
})