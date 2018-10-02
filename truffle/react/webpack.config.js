const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'development',
    entry: ['babel-polyfill', path.join(__dirname, 'src', 'index.js')],
    devServer: {
        contentBase: path.join(__dirname, 'src'),
        historyApiFallback: true,
    },
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'build.js'
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.join(__dirname, 'src', 'index.html'),
        })
    ],
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
                include: [/src/, /node_modules/]
            }, {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
                    presets: ['es2015', 'react', 'stage-2']
                }
            }, {
                test: /\.json$/,
                loader: 'json-loader',
                include: '/build/contracts/'
            }
        ]
    },
    resolve: {
        extensions: ['.js', '.json', '.jsx']
    }
};
