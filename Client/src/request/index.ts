import axios from 'axios'

const request = axios.create({
  withCredentials: true,
})

request.interceptors.request.use((config) => {
  return config
})

request.interceptors.response.use(
  (response) => {
    if (response.status !== 200)
      alert(`response.status: ${response.status}`)

    return response.data
  },
  (error) => {
    if (error.response.status === 401)
      window.location.reload()
  },
)

const a: API.Response<number> = {
  code: 1,
  message: '1',
  data: 'asd',
}
console.log(a)

export default request
