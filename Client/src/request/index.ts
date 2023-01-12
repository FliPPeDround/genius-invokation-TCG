import axios from 'axios'

const request = axios.create({
  baseURL: '/api',
  withCredentials: true,
  timeout: 5000,
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
export default request
