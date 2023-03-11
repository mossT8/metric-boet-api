import { Device, DeviceMapped } from "@/types/device/device";
import axios from "axios";
import authHeader from "@/services/auth-header";

const API_URL = "http://localhost:8080/api/v1/";

class DeviceService {
  addDevice(device: Device) {
    return axios.post(API_URL + "devices/add", device, {
      headers: authHeader(),
    });
  }
  getDeviceByUid(uid: string): Promise<DeviceMapped> {
    const mappedDeviced = axios
      .get<DeviceMapped>(API_URL + "devices/" + uid, {
        headers: authHeader(),
      })
      .then((response) => response.data);

    return mappedDeviced;
  }
  getAllDevicesForUser(): Promise<DeviceMapped[]> {
    const mappedDeviced = axios
      .get<DeviceMapped[]>(API_URL + "devices/list", { headers: authHeader() })
      .then((response) => response.data);

    return mappedDeviced;
  }
}

export default new DeviceService();
