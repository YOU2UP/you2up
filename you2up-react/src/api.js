import axios from "axios";

const api = axios.create({
    baseURL: "https://64371cc18205915d340512dc.mockapi.io/musicas"
})

export default api;