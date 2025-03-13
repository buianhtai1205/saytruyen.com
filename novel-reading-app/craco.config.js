const path = require('path');

module.exports = {
    webpack: {
        alias: {
            '@userComponents': path.resolve(__dirname, 'src/user/components'),
            '@api': path.resolve(__dirname, 'src/api')
        }
    }
};