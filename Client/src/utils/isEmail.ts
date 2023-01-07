export function isEmail(email: string): boolean {
  if (!email)
    return false
  return /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(email)
}
