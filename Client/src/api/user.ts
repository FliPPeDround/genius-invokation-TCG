import request from '@/request'

const getIsRegisteredByEmail = (email: string) => {
  return request<string, API.Response<boolean>>({
    url: '/getIsRegisteredByEmail',
    method: 'GET',
    data: email,
  })
}

export {
  getIsRegisteredByEmail,
}
