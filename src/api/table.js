import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'get',
    params
  })
}

export function listAlarm(params) {
  return request({
    url: '/vue-admin-template/test/listAlarm',
    method: 'get',
    params
  })
}


