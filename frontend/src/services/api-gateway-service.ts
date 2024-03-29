const baseApiShorthand = "http://localhost:8080/api";
const baseApiVersion = "/v2";

const baseDomain = baseApiShorthand + baseApiVersion;

const basePublicSubDomain = "/public/";
const basePrivateSubDomain = "/private/";

import { AuthHeader } from "@/types/auth/auth";
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";

export enum HTTP_PROTOCOLS {
  GET = "get",
  PUT = "put",
  POST = "post",
  PATCH = "patch",
  DELETE = "delete",
}

interface ApiRequest {
  packagePrefix: string;
  apiGroupKey: string;
  endpointClassName: string;
  payload: object;
  protocol: HTTP_PROTOCOLS;
  additionalProperties?: HeadersObject;
  public?: boolean;
}

interface HeadersObject {
  headers: {
    // Define the properties of the 'headers' object here
    Authorization: AuthHeader;
    // Add more properties as needed
    [x: string]: any;
  };
}
export interface EmptyReuqest {

}

export interface KeyRequest {
  key: string;
}

export interface IdRequest {
  id: number;
}

interface BasicApiResponse<T> {
  [x: string]: any;
  message: string;
  successful: boolean;
  data: T;
}

export class ApiGatewayService {
  private apiClient: AxiosInstance;

  constructor() {
    this.apiClient = axios.create({ baseURL: baseDomain });
  }

  private async makeRequest<T>(request: ApiRequest): Promise<T> {
    try {
      const url = request.public ? basePublicSubDomain : basePrivateSubDomain;

      const headers: HeadersObject = request.additionalProperties || { headers: {Authorization: {Authorization:""}} };      
      // Access the 'headers.headers' property directly since the type has been specified      
      

      const requestConfig: AxiosRequestConfig = {
        headers: headers.headers.Authorization,
      };

      const response: AxiosResponse<BasicApiResponse<T>> = await this.apiClient.post<BasicApiResponse<T>>(url, request, requestConfig);


      if (!response.data.body.successful) {
        console.log(response.data.body);
        // TODO: add global event for errors 
      }

      const data = response.data.body.data;

      return data;
    } catch (error) {
      throw error;
    }
  }

  public async callApiRequest<T>(
    packagePrefix: string,
    apiGroupKey: string,
    endpointClassName: string,
    payload: object,
    protocol: HTTP_PROTOCOLS,
    additionalProperties?: HeadersObject,
    open?: boolean
  ) {

    const request: ApiRequest = {
      apiGroupKey,
      endpointClassName,
      packagePrefix,
      protocol,
      payload,
      additionalProperties,
      public: open,
    };

    return this.makeRequest<T>(request);
  }
}
