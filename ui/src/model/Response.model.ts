export interface Response<T> {
    status: ResponseStatus;
    message: string;
    data: T;
}

export enum ResponseStatus {
    SUCCESS = 'SUCCESS',
    ERROR = 'ERROR'
}