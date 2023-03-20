import { useState } from 'react'

import reactLogo from '@/assets/react.svg'

import '@/styles/HomePage.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <div>
        <a href="https://reactjs.org" rel="noreferrer" target="_blank">
          <img alt="React logo" className="logo react" src={reactLogo} />
        </a>
      </div>
      <h1 className="bg-pink">Vite + React</h1>
      <div className="card">
        <button type="button" onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
      <h1 className="text-pink">Hello World</h1>
      <div className="bg-pink">왜 안돼</div>
      <h2 className="text-red font-cha text-supertitle">
        이것도 적용이 잘될까요?
      </h2>
    </div>
  )
}

export default App
