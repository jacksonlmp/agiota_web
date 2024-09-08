import axios from "axios";


const API_URL = 'http://localhost:8080/login';
export const api = axios.create({
    url: API_URL,
})