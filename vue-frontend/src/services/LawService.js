import axios, { Axios } from 'axios'

const LAW_API_BASE_URL = 'http://localhost:8080/api/laws/'

class LawService{
    async getLaws(){
        const response = await axios.get(LAW_API_BASE_URL);
        return response.data;
    }
}

export default new LawService()