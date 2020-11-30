const Mock = require('mockjs')

const data = Mock.mock({

  'items':[
  {
    tapeId:'12321'
  },
  {
    tapeId:'12333321'
  },
  {
    tapeId:'3333'
  }
]})


module.exports = [
  {
    url: '/vue-admin-template/test/listAlarm',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },

]
