const iconName = {
  anemo: 'anemo',
  cryo: 'cryo',
  dendro: 'dendro',
  electro: 'electro',
  geo: 'geo',
  hydro: 'hydro',
  pyro: 'pyro',
}

type IconNameType = keyof typeof iconName

function getIcon(iconName: IconNameType) {
  const picModules = import.meta.glob('./element/*.png', { eager: true })
  const icon = picModules[`./element/${iconName}.png`] as { default: string }
  return icon.default
}

function useRandomICon() {
  const icon = getIcon(Object.values(iconName)[Math.floor(Math.random() * 7)] as IconNameType)
  useFavicon(icon)
}

export function usePageIcon() {
  useRandomICon()
  setInterval(() => {
    useRandomICon()
  }, 2000)
}
