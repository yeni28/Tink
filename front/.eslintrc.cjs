module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  settings: {
    react: {
      version: 'detect',
    },
  },
  extends: [
    'eslint:recommended',
    'plugin:react/recommended',
    'plugin:@typescript-eslint/recommended',
    'plugin:react/jsx-runtime',
    'plugin:import/recommended',
    'plugin:import/typescript',
    'plugin:react-hooks/recommended',
    'plugin:prettier/recommended',
  ],
  overrides: [],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: ['react', '@typescript-eslint', 'import', 'react-hooks'],
  rules: {
    'react-hooks/rules-of-hooks': 'error',
    'react-hooks/exhaustive-deps': 'warn',
    'no-console': 'warn',
    'react/prop-types': 'off',
    'react/button-has-type': 'warn',
    'react/self-closing-comp': [
      'warn',
      {
        component: true,
        html: false,
      },
    ],
    'react/jsx-sort-props': [
      'warn',
      {
        shorthandFirst: true,
        callbacksLast: true,
        noSortAlphabetically: false,
        reservedFirst: true,
        multiline: 'last',
      },
    ],
    'react/no-unknown-property': [
      'error',
      { ignore: ['args', 'rotation', 'position', 'side', 'castShadow'] },
    ],
    'import/no-unresolved': 'off',
    'import/order': [
      'warn',
      {
        groups: [
          'builtin',
          'external',
          'internal',
          ['sibling', 'parent', 'index'],
          'type',
          'unknown',
        ],
        pathGroups: [
          {
            pattern: '{react*,react*/**}',
            group: 'external',
            position: 'before',
          },
          {
            pattern: '{./**/*.module.css,./**/*.css}',
            group: 'unknown',
          },
        ],
        pathGroupsExcludedImportTypes: ['react', 'unknown'],
        'newlines-between': 'always-and-inside-groups',
        alphabetize: {
          order: 'asc',
          caseInsensitive: true,
        },
      },
    ],
    'prettier/prettier': [
      'warn',
      {
        endOfLine: 'auto',
        // 화살표 함수 식 매개변수 () 생략 여부 (ex: (a) => a)
        arrowParens: 'always',
        htmlWhitespaceSensitivity: 'css',
        // 닫는 괄호(>) 위치 설정
        // ex: <div
        //       id="unique-id"
        //       class="contaienr"
        //     >
        bracketSameLine: false,
        // 객체 표기 괄호 사이 공백 추가 여부 (ex: { foo: bar })
        bracketSpacing: true,
        // 행폭 설정 (줄 길이가 설정 값보다 길어지면 자동 개행)
        printWidth: 80,
        // 산문 래핑 설정
        proseWrap: 'preserve',
        // 객체 속성 key 값에 인용 부호 사용 여부 (ex: { 'key': 'xkieo-xxxx' })
        quoteProps: 'as-needed',
        // 세미콜론(;) 사용 여부
        semi: false,
        // 싱글 인용 부호(') 사용 여부
        singleQuote: true,
        // 탭 너비 설정
        tabWidth: 2,
        // 객체 마지막 속성 선언 뒷 부분에 콤마 추가 여부
        trailingComma: 'es5',
        // 탭 사용 여부
        useTabs: false,
      },
    ],
  },
}
