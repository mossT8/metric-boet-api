import { Device } from '@/types/device';
import axios from 'axios';
import authHeader from '@/services/auth-header';

const API_URL = 'http://localhost:8080/api/v1/';

class DeviceService {
  addDevice(device: Device) {
    return axios.post(API_URL + 'devices/add', device, { headers: authHeader() });
  }
}


export default new DeviceService();
