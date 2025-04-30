import request from '@/utils/request'

// 查询工单操作日志列表
export function listLogs(query) {
  return request({
    url: '/city/logs/list',
    method: 'get',
    params: query
  })
}

// 查询工单操作日志详细
export function getLogs(id) {
  return request({
    url: '/city/logs/' + id,
    method: 'get'
  })
}

// 新增工单操作日志
export function addLogs(data) {
  return request({
    url: '/city/logs',
    method: 'post',
    data: data
  })
}

// 修改工单操作日志
export function updateLogs(data) {
  return request({
    url: '/city/logs',
    method: 'put',
    data: data
  })
}

// 删除工单操作日志
export function delLogs(id) {
  return request({
    url: '/city/logs/' + id,
    method: 'delete'
  })
}
