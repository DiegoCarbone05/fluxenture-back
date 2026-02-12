# Electron Integration

This project has been integrated with ElectronJS and TypeScript for desktop application development.

## Available Scripts

### Development
- `npm run electron-dev` - Runs both Angular dev server and Electron in development mode
- `npm run dev` - Alternative development script using Node.js

### Building
- `npm run build-electron` - Compiles TypeScript files for Electron
- `npm run electron-build` - Builds Angular app and creates Electron distribution
- `npm run electron-pack` - Builds Angular app and packages Electron app (without installer)

### Running
- `npm run electron` - Runs the compiled Electron app (requires build first)

## Project Structure

```
electron/
├── main.ts          # Main Electron process
├── preload.ts       # Preload script for secure IPC
└── tsconfig.json    # TypeScript config for Electron
```

## Development Workflow

1. **Development Mode**: Run `npm run electron-dev` to start both Angular and Electron
2. **Production Build**: Run `npm run electron-build` to create a distributable package
3. **Testing**: Run `npm run electron-pack` to test the packaged app locally

## Features

- TypeScript support for both Angular and Electron
- Secure IPC communication between main and renderer processes
- Cross-platform builds (Windows, macOS, Linux)
- Development tools integration
- Hot reload support in development mode

## Configuration

The Electron configuration is located in `package.json` under the `build` section. You can customize:
- App ID and product name
- Build targets for different platforms
- File inclusion/exclusion patterns
- Platform-specific settings
