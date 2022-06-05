import axios from 'axios'

const service = axios.create({
    responseType: 'arraybuffer'
})

service.interceptors.request.use(config=>{
    config.header['Authorization']=window.sessionStorage.getItem('tokenStr');
},error=>{
    console.log(error);
})
