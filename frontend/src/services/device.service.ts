import { Device, DeviceMapped } from "@/types/device/device";
import {
  ApiGatewayService,
  EmptyReuqest,
  HTTP_PROTOCOLS,
  KeyRequest,
} from "./api-gateway-service";
import authHeader from "@/services/auth-header";

const API_PACKAGE_PREFIX = "com.metric.boet.api.endpoints.closed";
const API_GROUP_KEY = "device";

class DeviceService {
  private apiService: ApiGatewayService;

  constructor() {
    this.apiService = new ApiGatewayService();
  }

  addDevice(device: Device) {
    const paylaod: Device = device;
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "CreateDevice",
      paylaod,
      HTTP_PROTOCOLS.POST,
      { headers: { Authorization: authHeader() } },
      false
    );
  }

  getDeviceByUid(uid: string): Promise<DeviceMapped> {
    const payload: KeyRequest = {key: uid};
    return this.apiService.callApiRequest<DeviceMapped>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "GetDevice",
      payload,
      HTTP_PROTOCOLS.GET,
      { headers: { Authorization: authHeader() } },
      false
    );
  }

  getAllDevicesForUser(): Promise<DeviceMapped[]> {
    const payload: EmptyReuqest = {};
    return this.apiService.callApiRequest<DeviceMapped[]>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "ListDevices",
      payload,
      HTTP_PROTOCOLS.POST,
      { headers: { Authorization: authHeader() } },
      false
    );
  }
}

export default new DeviceService();
