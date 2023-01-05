declare namespace API {
  interface Response<T> {
    code: number
    message: string
    data?: T
  }
}
