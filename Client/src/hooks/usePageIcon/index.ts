const iconName = {
  anemo: 'anemo',
  cryo: 'cryo',
  dendro: 'dendro',
  electro: 'electro',
  geo: 'geo',
  hydro: 'hydro',
  pyro: 'pyro',
} as const

type IconNameType = keyof typeof iconName

function getIcon(iconName: IconNameType) {
  const picModules = import.meta.glob('./element/*.png', { eager: true })
  const icon = picModules[`./element/${iconName}.png`] as { default: string }
  return icon.default
}

function useRandomICon() {
  const iconNameList = Object.values(iconName)
  const icon = getIcon(iconNameList[Math.floor(Math.random() * iconNameList.length)])
  useFavicon(icon)
}

export function usePageIcon() {
  useRandomICon()
  setInterval(() => {
    useRandomICon()
  }, 2000)
}
