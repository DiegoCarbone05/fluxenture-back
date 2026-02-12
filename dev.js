const { spawn } = require('child_process');
const path = require('path');

console.log('Starting development environment...');

// Start Angular development server
const angularProcess = spawn('ng', ['serve'], {
  stdio: 'inherit',
  shell: true
});

// Wait a bit for Angular to start, then start Electron
setTimeout(() => {
  console.log('Starting Electron...');
  const electronProcess = spawn('npm', ['run', 'electron'], {
    stdio: 'inherit',
    shell: true
  });

  // Handle process cleanup
  process.on('SIGINT', () => {
    console.log('\nShutting down development environment...');
    angularProcess.kill();
    electronProcess.kill();
    process.exit(0);
  });

  electronProcess.on('close', (code) => {
    console.log(`Electron process exited with code ${code}`);
    angularProcess.kill();
    process.exit(code);
  });

}, 5000); // Wait 5 seconds for Angular to start

angularProcess.on('close', (code) => {
  console.log(`Angular process exited with code ${code}`);
  process.exit(code);
});
