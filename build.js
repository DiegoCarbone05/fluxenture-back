const { execSync } = require('child_process');
const fs = require('fs');
const path = require('path');

console.log('Building Angular application...');
execSync('ng build --configuration production', { stdio: 'inherit' });

console.log('Building Electron application...');
execSync('tsc -p electron/tsconfig.json', { stdio: 'inherit' });

console.log('Build completed successfully!');
console.log('You can now run: npm run electron');
