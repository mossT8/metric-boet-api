import { Device, DeviceMapped } from "@/types/device/device";
import { ApiGatewayService, HTTP_PROTOCOLS } from "./api-gateway-service";
import authHeader from "@/services/auth-header";

const API_PACKAGE_PREFIX = "com.metric.boet.api.endpoints.closed";
const API_GROUP_KEY = "device";

class DeviceService {
  private apiService: ApiGatewayService;

  constructor() {
    this.apiService = new ApiGatewayService();
  }

  addDevice(device: Device) {
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "CreateDevice",
      device,
      HTTP_PROTOCOLS.POST,
      { headers: {Authorization: authHeader()} }, 
      false
    );
  }

  getDeviceByUid(uid: string): Promise<DeviceMapped> {
    return this.apiService.callApiRequest<DeviceMapped>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "GetDevice",
      { uid },
      HTTP_PROTOCOLS.GET,
      { headers: {Authorization: authHeader()} }, 
      false
    );
  }

  getAllDevicesForUser(): Promise<DeviceMapped[]> {
    return this.apiService.callApiRequest<DeviceMapped[]>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "ListDevices",
      {},
      HTTP_PROTOCOLS.POST,
      { headers: {Authorization: authHeader()} }, 
      false
    );
  }
}

export default new DeviceService();
