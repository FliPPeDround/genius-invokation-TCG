import request from '@/request'

const getIsRegisteredByEmail = (email: string) => {
  return request<string, API.Response<boolean>>({
    url: '/user/checkUserEmail',
    method: 'GET',
    data: { userEmail: email },
  })
}

export {
  getIsRegisteredByEmail,
}
