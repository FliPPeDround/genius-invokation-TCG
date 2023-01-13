import puppeteer from 'puppeteer'
import download from 'download'
import consola from 'consola'

type AvatarListType = { src: string; name: string }[]

async function getAvater(browser: puppeteer.Browser, url: string): Promise<AvatarListType> {
  const page = await browser.newPage()
  await page.goto(url, {
    timeout: 0,
  })

  const result = await page.$$eval(
    '#frame > div.content-wrap > div > div > div.character__main > div.character__page > div.swiper-container.character__swiper--page.swiper-container-initialized.swiper-container-horizontal.swiper-container-thumbs > ul > li',
    lines => lines.map((element) => {
      const src = element.querySelector('img').src
      const name = element.querySelector('p').innerText
      return {
        src,
        name,
      }
    }),
  )
  await page.close()
  return result
}

export async function getAvatars(areas: string[]): Promise<AvatarListType[]> {
  const browser = await puppeteer.launch({
    headless: false,
    userDataDir: './userData',
  })

  const result = await Promise.all(areas.map((area) => {
    // const url = `https://ys.mihoyo.com/main/character/${area}?char=0`
    // 海外地址
    const url = `https://genshin.hoyoverse.com/en/character/${area}?char=0`
    return getAvater(browser, url)
  }))

  await browser.close()
  return result
}

const AvatarList = await getAvatars(['mondstadt', 'liyue', 'inazuma', 'sumeru'])

AvatarList.forEach((avatars) => {
  avatars.forEach((avatar) => {
    download(avatar.src, './images', {
      filename: `${avatar.name}.png`,
    })
  })
})

consola.success('done')
