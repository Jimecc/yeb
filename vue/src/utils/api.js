import axios from 'axios'
import { Message } from 'element-ui';

axios.interceptors.response.use(success=>{
    // 业务逻辑错误
    if(success.status && success.status == 200){
        if(success.data.code == 500 || success.data.code == 401 || success.data.code == 403){
            Message.error({message:success.data.message});
            return;
        }
        if(success.data.message){
            Message.success({message:success.data.message});
        }
    }
    return success.data;

},error=>{

})


