import axios, { Axios } from 'axios'

const LAW_API_BASE_URL = 'http://localhost:8080/api/laws/'

class LawService{
    async getLaws(){
        return axios.get(LAW_API_BASE_URL);
    }
}

export default new LawService()